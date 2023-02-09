# clojupyter 0.3.6 docker image

This is an unofficial `Dockerfile` for running the latest [clojupyter](https://github.com/clojupyter/clojupyter) 0.3.6
to build a docker image to combine the power of Jupyter notebook and Clojure.

## Usage

This project is published on docker hub. You can pull the image by running:

```sh
docker run -p 8888:8888 nomorelint/jupyterlab-clojure-docker
```

The container will start up and the server will give you a url to copy into your browser to start a notebook session. It will look like:

```
http://127.0.0.1:8888/lab?token=1ec7646cd808fa45cefda01d43a34c8631f6577b6bb7a29b
```

## Building your own image

To build a docker image locally, use git clone to download the repo:
```sh
git clone https://github.com/nomorelint/jupyterlab-clojure-docker
```

You can then build the image from the repository root with:

```sh
docker build . -t nomorelint/jupyterlab-clojure-docker
```

After the build, use `docker run` to run the image.

## Using a host notebook folder

If you want to use notebooks from your host machine you'll need to mount a folder to the notebook volume as follows:

```sh
docker run -d \
    -p 8888:8888 \
    -v /path/to/local/notebooks/:/home/clojure/notebooks \
    nomorelint/jupyterlab-clojure-docker
```
