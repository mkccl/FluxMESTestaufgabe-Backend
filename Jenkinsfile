#!groovy

properties(
    [
        [$class: 'BuildDiscarderProperty', strategy:
          [$class: 'LogRotator', artifactDaysToKeepStr: '14', artifactNumToKeepStr: '5', daysToKeepStr: '30', numToKeepStr: '60']],
        pipelineTriggers(
          [
              pollSCM('H/15 * * * *'),
              cron('@daily'),
          ]
        )
    ]
)
node {
    stage('Checkout') {
        //disable to recycle workspace data to save time/bandwidth
        deleteDir()
        checkout scm

        //enable for commit id in build number
        //env.git_commit_id = sh returnStdout: true, script: 'git rev-parse HEAD'
        //env.git_commit_id_short = env.git_commit_id.take(7)
        //currentBuild.displayName = "#${currentBuild.number}-${env.git_commit_id_short}"
    }
  
    stage('Build') {
        milestone()
        sh 'mvn install'
    }

    stage('Archive') {
        sh 'tar -cvzf FluxMESTestaufgabe.tar.gz --strip-components=1 target/FluxMESTestaufgabe-Backend-0.0.1-SNAPSHOT.jar'
        archive 'FluxMESTestaufgabe.tar.gz'
    }

    stage('Deploy') {
        milestone()
        echo "Deploying..."
    }
    
    stage('Build image') {
       dockerImage = docker.build("derccl/flux_mes_testaufgabe_backend:latest")
    }
   
    stage('Push image') {
        withDockerRegistry([ credentialsId: "31391702-340f-4bef-a7fe-19420e3c03f6", url: "https://registry.hub.docker.com/repository/docker/derccl/flux_mes_testaufgabe_backend" ]) {
        dockerImage.push()
        }
    }
    
}
