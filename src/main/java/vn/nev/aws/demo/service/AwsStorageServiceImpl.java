package vn.nev.aws.demo.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service(value = "awsStorageService")
@PropertySource("classpath:application.yml")
public class AwsStorageServiceImpl implements AwsStorageService {

  @Value(value = "${s3.accessKeyId}")
  private String accessKeyId;

  @Value(value = "${s3.secretAccessKey}")
  private String sercretAccessKey;

  @Value(value = "${s3.bucketName}")
  private String bucketName;

  @Value(value = "${s3.region}")
  private String region;

  @Value(value = "${s3.folder}")
  private String folder;

  private static AmazonS3 s3client;

  private static AccessControlList aclWrite;

  private static AccessControlList aclReadWrite;

  private Logger logger = LoggerFactory.getLogger(AwsStorageServiceImpl.class);

  @PostConstruct
  public void init() {
    AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, sercretAccessKey);
    s3client = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region)
        .build();

    initACL();
    // a specified bucket name already exists, and therefore can't be used to create a new bucket
    if (!s3client.doesBucketExist(bucketName)) {
      s3client.createBucket(
          new CreateBucketRequest(bucketName, region).withAccessControlList(aclWrite));
    }
  }

  private void initACL() {
    aclWrite = new AccessControlList();
    aclWrite.grantPermission(GroupGrantee.AllUsers, Permission.Write);

    aclReadWrite = new AccessControlList();
    aclReadWrite.grantPermission(GroupGrantee.AllUsers, Permission.Read);
    aclReadWrite.grantPermission(GroupGrantee.AllUsers, Permission.Write);
  }

  @Override
  public void save(File file, String path) {
    s3client.putObject(
        new PutObjectRequest(bucketName, Paths.get(folder, path).toString(), file)
            .withAccessControlList(aclReadWrite));
  }

  @Override
  public void save(InputStream inputStream, String path) {
    String targetFolder = String.format("%s/%s", folder, path);
    s3client.putObject(
        new PutObjectRequest(bucketName, targetFolder, inputStream, null)
            .withAccessControlList(aclReadWrite));
    logger.info("Saved file to {}", targetFolder);
  }

  @Override
  public String getUrl(String fileName) {
    if (StringUtils.isEmpty(fileName)) {
      return fileName;
    }

    return s3client.getUrl(bucketName, String.format("%s/%s", folder, fileName)).toString();
  }
}
