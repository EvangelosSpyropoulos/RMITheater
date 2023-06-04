TH_CLS=classes/th
THCLNT_CLS=$(TH_CLS)/thclient
THSRV_CLS=$(TH_CLS)/thserver
TH_SRC=src/th
THCLNT_SRC=$(TH_SRC)/thclient
THSRV_SRC=$(TH_SRC)/thserver

all: $(TH_CLS)/THInterface.class $(THCLNT_CLS)/THClient.class $(THSRV_CLS)/THServer.class

$(THCLNT_CLS)/THClient.class: $(THCLNT_SRC)/*.java $(TH_CLS)/THInterface.class
	javac -cp classes -d classes $(THCLNT_SRC)/*.java

$(THSRV_CLS)/THServer.class: $(THSRV_SRC)/*.java $(TH_CLS)/THInterface.class
	javac -cp classes -d classes $(THSRV_SRC)/*.java

$(TH_CLS)/THInterface.class: $(TH_SRC)/*.java
	javac -d classes $(TH_SRC)/*.java

clean:
	rm $(TH_CLS)/*.class $(THCLNT_CLS)/*.class $(THSRV_CLS)/*.class