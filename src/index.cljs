(ns index
  (:require
   ["react-dom" :as rdom]
   [App :as App]))

(defonce elt (js/document.querySelector "#app"))

(rdom/render #jsx [App/App] elt)