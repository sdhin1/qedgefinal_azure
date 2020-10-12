pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //sh for mac
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh for mac
                bat "docker build -t=sdhingra13/qedgefinal ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh for mac
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push sdhingra13/qedgefinal:latest"
			    }                           
            }
        }
    }
}