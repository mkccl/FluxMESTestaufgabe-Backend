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
    
    stage('Push image') {
        withDockerRegistry([ credentialsId: "878c643b-5e11-45e5-bbac-2727982ea3a1", url: "" ]) {
        dockerImage.push()
        }
    }
    
}
