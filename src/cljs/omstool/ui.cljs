(ns omstool.ui
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.panel :as p]
            [om-bootstrap.grid :as g]
            [om-bootstrap.nav :as n]
            [om-bootstrap.random :as r]))


(defn render-nav-item [nav-item _]
  (reify
    om/IRender
    (render [_]
      (n/nav-item {:href (:href nav-item)} (:label nav-item)))))


(defn render-nav [nav _]
  (.log js/console nav)
  (reify
    om/IRender
    (render [_]
      (g/row nil
             (g/col {:md 12}
                    (n/navbar {:brand (dom/a {:href "#/"} "Admin")
                               :inverse? true}
                              (n/nav {:collapsible? true}
                                     (om/build-all render-nav-item (:links nav)))))))))


(defn render-footer [_ _]
  (reify
    om/IRender
    (render [_]
      (g/row nil
             (g/col {:md 12}
                    (p/panel nil ""))))))


(defn render-homepage [_ _]
  (reify
    om/IRender
    (render [_]
      (g/row nil
             (g/col {:md 12}
                    (r/jumbotron {}
                                 (dom/p "Welcome to FRED Admin")))))))

