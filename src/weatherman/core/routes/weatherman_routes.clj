(ns weatherman.core.routes.weatherman-routes
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST]]
            [weatherman.core.weather-fetcher :as wf]))

(defn get-weather [location]
  (-> location wf/local-req wf/get-resp))

(defroutes weatherman-routes
  (GET "/" [] "WEATHERMAN")
  (GET "/weather/:location" [location]
       (str "Welcome to " location)))
