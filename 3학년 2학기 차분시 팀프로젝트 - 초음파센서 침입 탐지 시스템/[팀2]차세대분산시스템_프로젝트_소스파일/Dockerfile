FROM ubuntu:16.04

RUN bash
RUN apt-get update -y
RUN apt-get install -y apt-utils 
RUN apt-get upgrade -y
RUN apt-get dist-upgrade -y
RUN apt-get install -y build-essential cmake pkg-config
RUN apt-get install -y libjpeg8-dev libtiff5-dev libjasper-dev libpng12-dev
RUN apt-get install -y libavcodec-dev libavformat-dev libswscale-dev libv4l-dev
RUN apt-get install -y libxvidcore-dev libx264-dev
RUN apt-get install -y libgtk-3-dev
RUN apt-get install -y libatlas-base-dev gfortran
RUN apt-get install -y python2.7-dev python3.5-dev
RUN apt-get install -y wget unzip
RUN apt-get install -y libgstreamer1.0-dev libgstreamer-plugins-base1.0-dev libgtk2.0-dev libgtk-3-dev
RUN apt-get install -y python-numpy python3-numpy
RUN apt-get install -y libqt4-dev 

RUN mkdir opencv && cd opencv && wget -O opencv.zip https://github.com/Itseez/opencv/archive/3.3.0.zip && unzip opencv.zip &&wget -O opencv_contrib.zip https://github.com/Itseez/opencv_contrib/archive/3.3.0.zip && unzip opencv_contrib.zip && cd opencv-3.3.0/ && mkdir build && cd build && cmake -D CMAKE_BUILD_TYPE=RELEASE \
-D CMAKE_INSTALL_PREFIX=/usr/local \
-D WITH_TBB=OFF \
-D WITH_IPP=OFF \
-D WITH_1394=OFF \
-D BUILD_WITH_DEBUG_INFO=OFF \
-D BUILD_DOCS=OFF \
-D INSTALL_C_EXAMPLES=ON \
-D INSTALL_PYTHON_EXAMPLES=ON \
-D BUILD_EXAMPLES=OFF \
-D BUILD_TESTS=OFF \
-D BUILD_PERF_TESTS=OFF \
-D WITH_QT=ON \
-D WITH_OPENGL=ON \
-D OPENCV_EXTRA_MODULES_PATH=../../opencv_contrib-3.3.0/modules \
-D WITH_V4L=ON  \
-D WITH_FFMPEG=ON \
-D WITH_XINE=ON \
-D BUILD_NEW_PYTHON_SUPPORT=ON \
../ && make -j2 && make install

RUN cat /etc/ld.so.conf.d/*
RUN pkg-config --modversion opencv
RUN pkg-config --libs --cflags opencv

COPY ./vmTest.py  / 
WORKDIR /

ENTRYPOINT [ "python" ]
CMD [ "vmTest.py" ]

