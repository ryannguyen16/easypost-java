## help - Display help about make targets for this Makefile
help:
	@cat Makefile | grep '^## ' --color=never | cut -c4- | sed -e "`printf 's/ - /\t- /;'`" | column -s "`printf '\t'`" -t

## build-release - Build the project for release
# @parameters:
# pass= - The GPG password to sign the release
build-release:
	mvn clean install -Dgpg.passphrase=${pass}

## build-dev - Build the project for development
build-dev:
	mvn clean install -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true

## publish - Publish a release of the project
# @parameters:
# pass= - The GPG password to sign the release
publish:
	mvn clean deploy -Dgpg.passphrase=${pass}

## test - Test the project
test:
	mvn --batch-mode install -Dgpg.skip=true -Dcheckstyle.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true

## clean - Clean the project
clean:
	mvn clean

# install-checkstyle - Install CheckStyle
install-checkstyle:
	wget -O checkstyle.jar -q https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.3.1/checkstyle-10.3.1-all.jar

## lint - Check if project follows CheckStyle rules (must run install-checkstyle first)
lint:
	java -jar checkstyle.jar src -c easypost_java_style.xml -d

## scan - Scan the project for serious security issues
scan:
	mvn verify -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Djavadoc.skip=true -Ddependency-check.failBuildOnCVSS=7 -Ddependency-check.junitFailOnCVSS=7

## scan-strict - Scan the project for any security issues (strict mode)
scan-strict:
	mvn verify -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Djavadoc.skip=true -Ddependency-check.failBuildOnCVSS=0 -Ddependency-check.junitFailOnCVSS=0

.PHONY: help build-release build-dev publish test clean install-checkstyle lint scan scan-strict
