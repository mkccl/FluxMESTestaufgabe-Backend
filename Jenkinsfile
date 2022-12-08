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
    
    stage('Stop old image') {
        script {
          try {
              echo "Stopping old image"
              sh 'docker container stop flux_mes_testaufgabe_backend'
              sh 'docker container rm flux_mes_testaufgabe_backend'
          } catch (Exception e) {
              echo 'Exception occurred: ' + e.toString()
          }
        }
    }
    
    stage('Start new image') {
        echo "Stopping old image"
        // -p 8080:8081
        sh 'cd /var/lib/jenkins/workspace/FluxMesTestAufgabe-Backend/'
        sh 'docker compose up -d --rm'
        //sh 'docker ps'
        //sh 'docker run --name flux_mes_testaufgabe_backend -itd derccl/flux_mes_testaufgabe_backend:latest --l traefik.enable=true --l traefik.http.routers.unbreakablev.entrypoints=web, websecure --l traefik.http.routers.unbreakablev.tls=true --l traefik.http.routers.unbreakablev.tls.certresolver=production'
    }
    
}
