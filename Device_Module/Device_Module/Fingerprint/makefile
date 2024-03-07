CC = g++
TARGET = finger_test
SOURCE = finger_test.cpp Adafruit_Fingerprint.cpp
OBJS = Adafruit_Fingerprint.h
OPTIONS = /usr/include/python3.11/
PY_OPTIONS = `python3-config --includes` `python3-config --libs`

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -I$(OPTIONS) $(PYOPTIONS) -o $(TARGET) $(SOURCE) -lpython3.11 -lutil -export-dynamic -lmosquitto

clean:
	rm -r finger_test __pycache__
