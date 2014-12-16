(ns weatherman.views.weatherman-views
  (:require [net.cgrand.enlive-html :as html]))

(html/defsnippet locations-template "templates/locations.html"
  [:body :div.content-wrapper]
  [locations]
  [:div.locations] (html/append (str "<p>" (apply str (interpose "<br>" locations)))))

(html/defsnippet nav-header "templates/header.html"
  [:body :div.header]
  [title]
  [:div.home-menu :a.header-title] (html/content title)) 

(html/deftemplate main-template "templates/main.html" [title locations]
  [:head :title] (html/content title)
  [:body :div.header :div.home-menu :a.header-title] (html/content title)
  [:body] (html/append (locations-template locations)))
