FROM ubuntu:22.04
LABEL maintainer="Brent Mercer <brent.e.mercer@gmail.com>"

ARG NB_USER=clojure
ARG NB_UID=1000

ENV HOME /home/${NB_USER}
ENV NOTEBOOK_PATH $HOME/notebooks
ENV CLOJUPYTER_PATH $HOME/clojupyter

ENV CLOJUPYTER_VERSION 0.3.6

ENV JUPYTER_ENABLE_LAB yes

ENV LEIN_ROOT 1

ENV PORT 8888

RUN rm -f /etc/apt/apt.conf.d/docker-clean \
    && apt update \
    && apt install -yq \
        python3 \
        python3-pip \
        python-dev-is-python3 \
        build-essential \
        curl \
        git-core \
        imagemagick \
        locales
RUN curl \
        -o jdk-19_linux-x64_bin.deb \
        https://download.oracle.com/java/19/latest/jdk-19_linux-x64_bin.deb \
    && apt-get -yq install \
        ./jdk-19_linux-x64_bin.deb \
    && update-alternatives --install \
        /usr/bin/java java \
        /usr/lib/jvm/jdk-19/bin/java \
        1919
RUN curl \
        -o /usr/local/bin/lein \
        https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein \
    && chmod +x /usr/local/bin/lein \
    && lein self-install

COPY requirements.txt .
RUN pip install -r requirements.txt

RUN adduser --disabled-password \
        --gecos "Default user" \
        --uid ${NB_UID} \
        --home ${HOME} \
        ${NB_USER} \
    && chown -R ${NB_USER}:${NB_USER} ${HOME}

# io.simplect/compose uses greek letters and requires a utf-8 locale to be set
# locale-setting config borrowed from https://stackoverflow.com/questions/28405902/
RUN sed -i -e \
        's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' \
        /etc/locale.gen \
    && dpkg-reconfigure --frontend=noninteractive locales \
    && update-locale LANG=en_US.UTF-8

ENV LANG en_US.UTF-8 

USER ${NB_USER}
WORKDIR ${HOME}
RUN mkdir -p $NOTEBOOK_PATH \
    && git clone https://github.com/clojupyter/clojupyter.git $CLOJUPYTER_PATH

# Install clojupyter
WORKDIR $CLOJUPYTER_PATH

RUN git checkout $CLOJUPYTER_VERSION
COPY project.clj .
RUN make \
    && make install \
    && rm -rf $CLOJUPYTER_PATH

WORKDIR $NOTEBOOK_PATH

EXPOSE $PORT
VOLUME $NOTEBOOK_PATH
CMD ["jupyter", "lab", "--ip=0.0.0.0", "--no-browser"]
