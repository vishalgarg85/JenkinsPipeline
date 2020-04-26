def pipelineTemplate() {
try {
	stage('Git-Checkout') {
			try {
					echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
					echo 'Checking out from Git repo'
					git 'https://github.com/vishalgarg85/Pipeline_Script.git'
					}
			catch(Exception e) {
					echo 'Something didnt work and got some exceptions'
					throw e
					}
			}
		stage('Build') {
			try {
					echo 'Building the checked-out project'
					bat label: '', script: 'Build.bat'
					}
			catch(Exception e) {
					echo 'Something didnt work and got some exceptions'
					throw e;
					}
			}
		stage('Unit-Test') {
			try {
					echo 'Running JUnit Tests'
					bat label: '', script: 'Unit.bat'
					}
			catch(Exception e) {
					echo 'Something didnt work and got some exceptions'
					throw e;
					}
			}
		stage('Quality-Gate') {
			try {
					echo 'Verifying Quality Gates'
					bat label: '', script: 'Quality.bat'
					}
			catch(Exception e) {
					echo 'Something didnt work and got some exceptions'
					throw e
					}
			}
		stage('Deploy') {
			try {
					echo 'Deploy to stage environment for more tests!'
					bat label: '', script: 'Deploy.bat'
					}
			catch(Exception e) {
					echo 'Something didnt work and got some exceptions'
					throw e
					}
			}
	}
catch (Exception e) {
			throw e
	}
finally {
		//deleteDir();
	}
}

return this;
