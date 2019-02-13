VERSION=`cut -d '"' -f2 $BUILDDIR/../version.js`
ANDROID_HOME=/run/media/xziy/4641dd23-379f-479c-8c39-bcb6d883ad58/AND/sdk
ifeq ($(UNAME), Linux)
  SHELLCMD := bash
endif

ifeq ($(UNAME), Darwin)
  SHELLCMD := sh
endif



recompile:
	rm -rf ./BitcoinChecker_APK_OUT/depack/*
	rm -rf ./BitcoinChecker_APK_OUT/out/*
	apktool d -f -r ./BitcoinChecker_APK_OUT/bc_originl.apk -o ./BitcoinChecker_APK_OUT/depack/bc
	apktool d -f -r ./dataModuleTester/build/outputs/apk/dataModuleTester-debug.apk -o ./BitcoinChecker_APK_OUT/depack/bc_tester
#	rm -rf ./BitcoinChecker_APK_OUT/depack/bc/smali/com/mobnetic/
	cp -rf ./BitcoinChecker_APK_OUT/depack/bc_tester/smali/com/mobnetic/* ./BitcoinChecker_APK_OUT/depack/bc/smali/com/mobnetic/
	apktool b ./BitcoinChecker_APK_OUT/depack/bc -o ./BitcoinChecker_APK_OUT/out/bc.apk
	echo "!!!!!!!!! KEY: 12qwaszx"
	jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore bc.jks -tsa http://sha256timestamp.ws.symantec.com/sha256/timestamp -signedjar ./BitcoinChecker_APK_OUT/out/bc-signed.apk  ./BitcoinChecker_APK_OUT/out/bc.apk bc
	$(ANDROID_HOME)/build-tools/25.0.3/zipalign -v 4 ./BitcoinChecker_APK_OUT/out/bc-signed.apk ./BitcoinChecker_APK_OUT/out/bc-signed-aligned.apk

