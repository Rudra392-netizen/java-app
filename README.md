# Project Title

A brief description of what this project does and who it's for

# **🚀 Spring Framework Java App**

A simple **Spring Boot** application built with **Java 17**, packaged using **Maven**, and deployed using a complete CI/CD pipeline with **Jenkins**, **SonarQube**, **Docker**, **Trivy**, and **DockerHub**.

---

## **📸 CI/CD Pipeline Overview**

The pipeline is defined using **Jenkins Declarative Pipeline** and includes:

- ✅ Code Checkout (Git)
- ✅ Build with Maven
- ✅ SonarQube Analysis
- ✅ Docker Image Build
- ✅ Image Scan using Trivy
- ✅ Push to DockerHub

## **📁 Project Structure**

java-app/
├── src/ # Source code

├── Dockerfile # Docker instructions

├── Jenkinsfile # Jenkins pipeline

├── pom.xml # Maven config

├── README.md # Project documentation

└── screenshots/ # Project screenshots (like pipeline)



---

## **🧰 Tech Stack**

| **Technology**  | **Purpose**                         |
|------------------|--------------------------------------|
| Java 17          | Programming Language                 |
| Spring Boot      | Backend Framework                    |
| Maven            | Build Tool                           |
| Jenkins          | CI/CD Automation                     |
| SonarQube        | Code Quality Analysis                |
| Docker           | Containerization                     |
| Trivy            | Vulnerability Scanning               |
| DockerHub        | Docker Image Repository              |
| GitHub           | Source Code Hosting                  |

---
## **📋 Prerequisites**

Before running the project, ensure you have the following installed:

## 🧰 Install Java 17 (OpenJDK)

sudo apt update

sudo apt install openjdk-17-jdk -y

java --version


## 🧰 Install Maven

sudo apt update

sudo apt install maven -y

mvn -version

## 🧰 Install Git

sudo apt update

sudo apt install git -y

git --version



## 🐳 Install Docker

## Install in Amazon Ubuntu

#!/bin/bash

sudo apt update -y

sudo apt install apt-transport-https ca-certificates curl software-properties-common -y

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable" -y

sudo apt update -y

apt-cache policy docker-ce -y

sudo apt install docker-ce -y

#sudo systemctl status docker

sudo chmod 777 /var/run/docker.sock

## 🔧 Install Jenkins (LTS)

#!/bin/bash

#-f Fail silently (no output at all) on server errors.
#s--silent Silent or quiet mode. Don't show progress meter or error messages.
#-S, --show-error and when used with -s , it will show an error
#-L, --location If the server reports that the requested page has moved to a different location
#A System/software update is typically a release containing enhancements to the current version. 
#An upgrade is a whole new version of software that represents a significant change or major improvement.
#DEB - A deb package (.deb file) is a software package in a specific format designed for Debian-based distributions recognized by the .deb extension. \
#Deb packages allow installing local software on an Ubuntu system.





sudo apt update -y

sudo apt upgrade -y 

sudo apt install openjdk-17-jre -y

curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

sudo apt-get update -y 

sudo apt-get install jenkins -y

## 🧪 Install Trivy (Security Scanner)
# A Simple and Comprehensive Vulnerability Scanner for Containers and other Artifacts, Suitable for CI.

sudo apt-get install wget apt-transport-https gnupg lsb-release

wget -qO - https://aquasecurity.github.io/trivy-repo/deb/public.key | sudo apt-key add -

echo deb https://aquasecurity.github.io/trivy-repo/deb $(lsb_release -sc) main | sudo tee -a /etc/apt/sources.list.d/trivy.list

sudo apt-get update

sudo apt-get install trivy


## **🚀 How to Run the Application**

### **Run Locally with Maven**

git clone https://github.com/Rudra392-netizen/java-app.git

cd java-app

mvn clean install

mvn spring-boot:run

Run with Docker

docker build -t java-app:latest .

docker run -p 8000:8000 java-app


## ✅ SonarQube Quality Gate
Spring Framework Java App: ✅ Passed
Server-Side Processing: ✅ Success

🔁 Jenkins CI/CD Pipeline Stages
Stage	                  Tool	           Purpose
Checkout SCM            Git	         Clones project from GitHub

Build with Maven        Maven	       Compiles & builds the app

SonarQube Analysis	   SonarQube	   Static code quality check

Build Docker Image	   Docker	     Creates container image

Vulnerability Scan	   TrivyScans   Docker image for CVEs

Push Image to DockerHub	 DockerHub	Publishes image to remote registry

## 📦 DockerHub

Optional — Update with your DockerHub username.

docker pull your-username/java-app

## 📄 License
Licensed under the MIT License.
See the LICENSE file for details.

## 🙏 Acknowledgements

Spring Boot

Jenkins

SonarQube

Trivy

Docker

GitHub

## 👨‍💻 Maintainer

Rudra Pratap Singh

🔗 GitHub: Rudra392-netizen

## Github_url:

https://github.com/Rudra392-netizen/java-app.git

python

---

### ✅ Next Steps:

- Add the image to: `screenshots/jenkins_pipeline.png`
- 
- Save this `README.md` in your repo root
- 
- Commit & push

I'm happy to help with those too.
