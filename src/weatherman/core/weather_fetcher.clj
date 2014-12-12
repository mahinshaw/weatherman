(ns weatherman.core.weather-fetcher
  (:require [clojure.string :as str]
            [org.httpkit.client :as http]
            [cheshire.core :refer :all]))

(def wwo-api-local "http://api.worldweatheronline.com/free/v2/weather.ashx")
(def wwo-api-search "http://api.worldweatheronline.com/free/v2/search.ashx")

(def api-key (str/trim (slurp "config/wwo_api_key")))

(def req (http/request {:url wwo-api-local
                        :method :get
                        :query-params {:key api-key
                                       :q "Greensboro NC"
                                       :num_of_days "1"
                                       :format "json"}}))

(let [resp req]
  (println "Status: " (:status @resp))
  (println "Body: " (:body @resp)))

(defn local-req [location days]
  (http/request {:url wwo-api-local
                 :method :get
                 :query-params {:key api-key
                                :q location
                                :num_of_days days
                                :format "json"}}))

(defn get-resp [request]
  "Take a request and return the body as a hash-map"
  (let [resp request]
    (parse-string (:body @resp) true)))
