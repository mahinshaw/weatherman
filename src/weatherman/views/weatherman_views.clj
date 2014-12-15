(ns weatherman.views.weatherman-views
  (:require [net.cgrand.enlive-html :as html]))

(html/defsnippet nav-header "templates/header.html"
  [:body :div.header]
  [title]
  [:div.home-menu :a.header-title] (html/content title)) 

(html/deftemplate main-template "templates/main.html" [title]
  [:head :title] (html/content title)
  [:body] (html/do-> (html/append (nav-header title))))
