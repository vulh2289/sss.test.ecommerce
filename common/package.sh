#!/bin/bash
set -e

OUTPUT_DIR="../deb-packaging"
BUILD_DIR="$OUTPUT_DIR/deploy"
SRC_ROOT=".."
DROPBOX_ROOT="$HOME/Dropbox/Adoreboard_External_Collaboration"
deb_arch=`dpkg-architecture | perl -ne '/DEB_BUILD_ARCH=(\S+)/&&print$1'`
VERSION="0.4"

# Tomcat files
rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/var/lib/tomcat7/webapps/
cp $SRC_ROOT/api/target/api-0.4.2.war $BUILD_DIR/var/lib/tomcat7/webapps/$VERSION.war

mkdir -p $BUILD_DIR/etc/tomcat7/
cp $SRC_ROOT/api/src/main/resources/tomcat-config/adoreboard-self-signed.jks $BUILD_DIR/etc/tomcat7/
cp $SRC_ROOT/api/src/main/resources/tomcat-config/adoreboard-ssl.patch $BUILD_DIR/etc/tomcat7/

# Configuration files in /opt/adoreboard

# !!!! - NOTE COMMENTED OUT - THESE FILES SHOULD ONLY BE INSERTED ON A BLANK SYSTEM - WRAP IN "if" statement? 
# mkdir -p $BUILD_DIR/opt/adoreboard/configuration
# cp $SRC_ROOT/api/src/main/resources/configuration/config.properties $BUILD_DIR/opt/adoreboard/configuration

# Set ADOREBOARD_HOME environment variable on startup
# This command seems to be insufficient i.e. do not know why, but it does not work for e.g. Ferg, Stephen
# Have added alternative manual steps to README.md
mkdir -p $BUILD_DIR/etc/profile.d/
cp $SRC_ROOT/api/src/main/resources/configuration/adoreboard.sh  $BUILD_DIR/etc/profile.d/

#--------------------------------------
# Now build a debian package
#--------------------------------------
mkdir -p $BUILD_DIR/DEBIAN

cat > "$BUILD_DIR/DEBIAN/control" <<EOT
Package: adoreboard-api
Version: $VERSION
Architecture: ${deb_arch}
Maintainer: Adoreboard <office@adoreboard.com>
Depends: tomcat7, patch, phantomjs
Description: API for Adoreboard product
EOT

cat > "$BUILD_DIR/DEBIAN/postrm" <<'EOT'
#! /bin/sh
# postrm script
    VERSION="0.4"

    # Remove Tomcat deployment
    rm -rf /var/lib/tomcat7/webapps/$VERSION

    #DEBHELPER#
exit 0
EOT

cat > "$BUILD_DIR/DEBIAN/postinst" <<'EOT'
#! /bin/sh
# postinst script
	#Set Java_HOME if not already set
	echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle" > /etc/profile.d/adoreboard_java_home.sh

	# Ensure ADOREBOARD_HOME environment variable is set on startup
	chmod +x /etc/profile.d/adoreboard.sh

	# Configure tomcat for SSL if not already done

	# Check if tomcat is already hosting an SSL port on 8443
	# If so, don't change anything,
	# If not, add our self-signed certificate
	service tomcat7 start

	grep_output=$(netstat -ntl|grep ":8443")
	if [ $? -ne 0 ]; then
		echo "Configuring Tomcat to use Adoreboard self-signed SSL certificate"
		(cd /etc/tomcat7; patch server.xml < adoreboard-ssl.patch)
		service tomcat7 restart
	fi

    #DEBHELPER#
exit 0
EOT

chmod 755 $BUILD_DIR/DEBIAN/*

rm -f $OUTPUT_DIR/adoreboard-api*.deb
dpkg-deb --build $BUILD_DIR \
    $OUTPUT_DIR/adoreboard-api_${VERSION}.`eval date +%Y%m%d%H%M`_${deb_arch}.deb
