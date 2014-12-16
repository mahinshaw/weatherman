(ns weatherman.routes.weatherman-routes
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST]]
            [weatherman.weather-fetcher :as wf]
            [weatherman.views.weatherman-views :refer [main-template]]))

(def locations (atom '("Greensboro NC" "Stockholm")))
(def title "Weatherman")

;; :data then (:current_condition :request :weather)
(defn fetch-weather [location]
  (-> location wf/local-req wf/get-resp))

(defn get-current-condition [location]
  (-> location fetch-weather (get-in [:data :current_condition]) first))

(defn get-weather [location]
  (-> location fetch-weather (get-in [:data :weather]) first))

;; :search_api then :result then an array of maps
(defn fetch-search-results [location]
  (-> location wf/search-req wf/get-resp))

(defn get-search [location]
  (-> location fetch-search-results :search_api :result))

(defn post-location [request]
  (let [location (get-in request [:params :location])]
    (swap! locations conj location)
    (response/redirect "/")))

(defn return-current-conditions [locations]
  (for [loc locations] {:location loc
                        :current_condition (get-current-condition loc)}))

(defn index [request]
  (main-template title (return-current-conditions @locations)))

(defroutes weatherman-routes
  (GET "/" [] index)
  (GET "/search/:location" [location]
       (str "<h3>Found 10 results: </h3><br/>" (get-search location)))
  (GET "/weather/:location" [location]
       (str "<h3>Welcome to " location "</h3>" "<br/>" (get-weather location)))
  (GET "/cc/:location" [location]
       (str "<h3>Welcome to " location "</h3>" "<br/>" (get-current-condition location)))
  (POST "/new" [] post-location))
