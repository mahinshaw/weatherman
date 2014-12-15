(defproject weatherman "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.2.0"]
                 [http-kit "2.1.19"]
                 [cheshire "5.4.0"]
                 [prone "0.6.1"]
                 [ring/ring-defaults "0.1.2"]
                 [enlive "1.1.5"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler weatherman.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
