(ns weatherman.views.weatherman-views
  (:require [net.cgrand.enlive-html :as html]))

(html/defsnippet location-template "templates/location.html"
  [:div.location]
  [location]
  [:div.location :div.locale] (html/content (:location location))
  [:div.location :div.current-condition :div.temp] (html/content (get-in location [:current_condition :temp_F])))

(defn show-locations [locations]
  "Show each of location in the list of locations."
  (html/at (html/html-resource "templates/location.html")
           [:div.location] (html/clone-for [loc locations] (html/content (location-template loc)))))

(html/deftemplate main-template "templates/main.html" [title locations]
  [:head :title] (html/content title)
  [:body :div.content-wrapper :div.locations] (html/append (show-locations locations))
  )
