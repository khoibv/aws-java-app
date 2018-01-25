package vn.nev.aws.demo.service;

import java.io.File;
import java.io.InputStream;

public interface AwsStorageService {

  void save(File file, String path);

  void save(InputStream inputStream, String path);

  String getUrl(String fileName);

}
