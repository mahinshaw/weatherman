(ns weatherman.weather-fetcher
  (:require [clojure.string :as str]
            [org.httpkit.client :as http]
            [cheshire.core :refer :all]))

(def wwo-api "http://api.worldweatheronline.com/free/v2")
(def wwo-api-local (str/join "/" (cons wwo-api '("weather.ashx"))))
(def wwo-api-search (str/join "/" (cons wwo-api '("search.ashx"))))

(def api-key (str/trim (slurp "config/wwo_api_key")))

;; The local weather request starts with the "data" key (:data)
;; Below :data are => :current_condition :request :weather
(defn local-req
  "Take a location, and a number of days, to create a request"
  ([location] (local-req location "1"))
  ([location days]
   (http/request {:url wwo-api-local
                  :method :get
                  :query-params {:key api-key
                                 :q location
                                 :num_of_days (str days)
                                 :format "json"}})))

(defn search-req
  "Use search api to look for a specific location."
  ([location] (search-req location true))
  ([location time-zone?] (search-req location time-zone? 10))
  ([location time-zone? num-results]
   (http/request {:url wwo-api-search
                  :method :get
                  :query-params {:key api-key
                                 :q location
                                 :timezone (if time-zone? "yes" "no")
                                 :num_of_results num-results
                                 :format "json"}})))

(defn get-resp [request]
  "Take a request and return the body as a hash-map"
  (when-let [resp request]
    (parse-string (:body @resp) true)))

(defn weather-aggregator
  "For the needs of this app, wwo provides less information that desirable. This func aggregates the resp.
The map will look like the following:
{:"
  ([location] (->> "7" (weather-aggregator location)))
  ([location days]
   (let [resp-body (-> location (local-req days) get-resp)]
     )))

