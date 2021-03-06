(defproject finja "0.1.0-SNAPSHOT"
  :description "Namespace for handling browser URL, History and Address bar."
  :url "https://github.com/wnr/finja"
  :license nil
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]]

  :plugins [[lein-figwheel "0.5.14"]
            [lein-cljsbuild "1.1.4" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src"]

                ;; the presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel     {;; :on-jsload "example-app.main/on-js-reload"
                               ;; :open-urls will pop open your application
                               ;; in the default browser once Figwheel has
                               ;; started and complied your application.
                               ;; Comment this out once it no longer serves you.
                               :open-urls ["http://localhost:3449/index.html"]}

                :compiler     {:main                 example.main
                               :asset-path           "js/compiled/out"
                               :output-to            "resources/public/js/compiled/example-app.js"
                               :output-dir           "resources/public/js/compiled/out"
                               :source-map-timestamp true
                               ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                               ;; https://github.com/binaryage/cljs-devtools
                               :preloads             [devtools.preload]
                               :optimizations        :none}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             :server-port 3449                              ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs    ["resources/public/css"]}          ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this

             ;; doesn't work for you just run your own server :) (see lein-ring)

             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you are using emacsclient you can just use
             ;; :open-file-command "emacsclient"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"



  ;; setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl


  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.8"]
                                  [figwheel-sidecar "0.5.14"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [org.clojure/tools.namespace "0.2.11"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {; for nREPL dev you really need to limit output
                                  :init             (set! *print-length* 50)
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
