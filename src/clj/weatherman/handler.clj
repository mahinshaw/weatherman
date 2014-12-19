(ns weatherman.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [prone.middleware :as prone]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [weatherman.routes.weatherman-routes :refer [weatherman-routes]]))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes weatherman-routes app-routes)
    (prone/wrap-exceptions {:app-namespaces ['weatherman.weather-fetcher]})
    (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
