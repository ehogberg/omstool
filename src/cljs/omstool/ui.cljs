(ns omstool.ui
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.panel :as p]
            [om-bootstrap.grid :as g]
            [om-bootstrap.nav :as n]
            [om-bootstrap.random :as r]))


(defn render-nav [app owner]
  (reify
    om/IRender
    (render [_]
      (n/navbar {:brand (dom/a {:href "#/"} "Admin")
                 :inverse? true}
                (n/nav {:collapsible? true}
                 (n/nav-item {:href "#/companies"} "Companies"))))))

(defn render-footer [_ _]
  (reify
    om/IRender
    (render [_]
      (p/panel nil ""))))


(defn render-homepage [_ _]
  (reify
    om/IRender
    (render [_]
      (r/jumbotron {}
                   (dom/p "Welcome to FRED Admin")))))

