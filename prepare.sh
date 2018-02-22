#!/bin/sh
echo Update AMI
sudo yum -y install update

sudo yum -y install java-1.8.0-openjdk-src.x86_64
sudo yum -y remove java-1.7.0-openjdk
sudo mkdir /data01 /data01/tomcat
sudo wget http://mirror.downloadvn.com/apache/tomcat/tomcat-9/v9.0.5/bin/apache-tomcat-9.0.5.tar.gz -P /data01
sudo tar -xvf /data01/apache-tomcat-9.0.5.tar.gz -C /data01/tomcat --strip-components=1
sudo rm /data01/apache-tomcat-* -Rf

sudo chown ec2-user:ec2-user /data01 -R

echo =============================
echo Installed Java 8 and Tomcat 9
echo Please check directory /data01/tomcat
