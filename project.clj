(defproject weatherman "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj" "src/cljs"]

  :min-lein-version "2.5.0"

  :dependencies [;; Clojure related
                 [org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [http-kit "2.1.19"]
                 [cheshire "5.4.0"]
                 [prone "0.8.0"]
                 [ring "1.3.2"]
                 [ring/ring-defaults "0.1.2"]
                 [enlive "1.1.5"]
                 [selmer "0.7.7"]
                 [environ "1.0.0"]

                 ;; Clojurescript related
                 [org.clojure/clojurescript "0.0-2496"]
                 [figwheel "0.2.0-SNAPSHOT"]
                 [secretary "1.2.1"]]

  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :ring {:handler weatherman.handler/app}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to "resources/public/js/app.js"
                                        :output-dir "resources/public/js/out"
                                        :output-map "resources/public/js/out.js.map"
                                        :preamble ["reagent/react.js"]
                                        ;; externs contains js we want included into the compile step.
                                        :externs [] 
                                        :optimizations :none
                                        :pretty-print true}}}}

  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]

                   :plugins [[lein-figwheel "0.2.0-SNAPSHOT"]]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:dev true}

                   :repl-options {:init-ns weatherman.handler}}
             })
