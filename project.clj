(defproject clojupyter "0.3.6"
  :description			"A Jupyter kernel for Clojure"
  :license			{:name "MIT"}
  :url				"https://github.com/clojupyter/clojupyter"


  :scm                          {:name "git" :url "https://github.com/clojupyter/clojupyter"}
  :source-paths                 ["src"]
  :resource-paths               ["resources"]
  :profiles                     {:dev           {:dependencies  [[midje "1.9.6" :exclusions [org.clojure/clojure]]]
                                                 :plugins       [[lein-midje "3.2.1"] [com.roomkey/lein-v "7.0.0"]]}
                                 :uberjar       {:aot :all}}

  :main                         clojupyter.kernel.core
  :aot                          [clojupyter.cmdline]

  ;; The aliases below can be invoked with 'lein <alias>':
  :aliases                      {"clojupyter"                   ["run" "-m" "clojupyter.cmdline"]
                                 "update-version-edn"           ["v" "cache" "resources/clojupyter/assets" "edn"]}
  
  :dependencies [[cheshire/cheshire			"5.11.0"]
                 [cider/cider-nrepl "0.26.0"]
                 [clojure.java-time/clojure.java-time	"1.1.0"]
                 [com.cemerick/pomegranate	"1.1.0"]
                 [com.grammarly/omniconf "0.4.3"]
                 [com.taoensso/timbre "5.2.1"]
                 [io.aviso/pretty "1.1.1"]
                 [hiccup/hiccup "2.0.0-alpha2"]
                 [io.forward/yaml "1.0.11" :exclusions [org.flatland/ordered]]
                 [org.flatland/ordered "1.15.10"]
                 [incanter "1.9.3"]
                 [io.pedestal/pedestal.interceptor "0.5.10"]
                 [io.simplect/compose "0.7.27"]
                 [me.raynes/fs "1.4.6"]
                 [net.cgrand/parsley "0.9.3" :exclusions [org.clojure/clojure]]
                 [net.cgrand/regex "1.1.0" :exclusions [org.clojure/clojure]]
                 [net.cgrand/sjacket "0.1.1" :exclusions [org.clojure/clojure net.cgrand/parsley]]
                 [nrepl/nrepl "1.0.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/data.codec "0.1.1"]
                 [org.clojure/data.json "2.4.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.clojure/test.check "1.1.1"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.xerial/sqlite-jdbc "3.39.3.0"]
                 [org.zeromq/jeromq "0.5.2"]
                 [pandect/pandect "1.0.2"]
                 [org.clojure/tools.analyzer "1.1.0"]
                 [slingshot/slingshot "0.12.2"]
                 [zprint/zprint "1.2.4"]
                 [org.clojure/core.async "1.5.648"]
                 [com.fzakaria/slf4j-timbre "0.3.21"]
                 [org.slf4j/log4j-over-slf4j "2.0.3"]
                 [org.slf4j/jul-to-slf4j "2.0.3"]
                 [org.slf4j/jcl-over-slf4j "2.0.3"]]
  )
