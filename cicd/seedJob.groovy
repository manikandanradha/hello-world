def mainFolderName = "manikandan"
def folderName = "hello-world"
def pipelineDetails = [
  [name: "01-echo-helloworld", script_path: "cicd/01-Jenkinsfile-helloworld"],
  [name: "02-maven-build", script_path: "cicd/02-Jenkinsfile-maven-build"],
  [name: "03-maven-build-tools", script_path: "cicd/03-Jenkinsfile-maven-build-tools"],
  [name: "04-maven-triggers-hook",script_path: "cicd/04-Jenkinsfile-maven-triggers-hook"]
]

folder(mainFolderName) {
  displayName(folderName)
  folder(folderName) {}
}

for (pipelineDetail in pipelineDetails) {
  pipelineJob("${mainFolderName}/${folderName}/${pipelineDetail.name}") {
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