# clojupyter 0.3.6 docker image

This is a non official `Dockerfile` for [clojupyter](https://github.com/clojupyter/clojupyter)
to build a docker image to combine the power of Jupyter notebook and Clojure.


## Usage

This project is published on docker hub. You can try it by pulling the image with:

```sh
docker run -p 8888:8888 nomorelint/jupyterlab-clojure-docker
```

The container will start up and the server will give you a url to copy into your browser to start a notebook session. It will look like:

```
http://127.0.0.1:8888/lab?token=1ec7646cd808fa45cefda01d43a34c8631f6577b6bb7a29b
```

Alternatively you can first build the image yourself from the repository root with

```sh
docker build . -t nomorelint/jupyterlab-clojure-docker
```

If you want to use notebooks from your host machine you'll need to mount a folder to the notebooks volume as follows:

```sh
docker run -d \
    -p 8888:8888 \
    -v /path/to/local/notebooks/:/home/clojure/notebooks \
    nomorelint/jupyterlab-clojure-docker
```
