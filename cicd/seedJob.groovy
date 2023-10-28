def folderName = "hello-world"
def pipelineDetails = [
  [name: "01-echo-helloworld", script_path: "cicd/01-Jenkinsfile-helloworld"],
  [name: "02-maven-build", script_path: "cicd/02-Jenkinsfile-maven-build"],
  [name: "03-maven-build-tools", script_path: "cicd/03-Jenkinsfile-maven-build-tools"],
]

folder(folderName) {
  displayName(folderName)
}

for (pipelineDetail in pipelineDetails) {
  pipelineJob("${folderName}/${pipelineDetail.name}") {
    definition {
      cpsScm {
        scm{
          git {
            branch('main')
            remote{
              url('https://github.com/manikandanradha/hello-world.git')
            }
          }
        }
        lightweight(true)
        scriptPath(pipelineDetail.script_path)
      }
    }
  }
}