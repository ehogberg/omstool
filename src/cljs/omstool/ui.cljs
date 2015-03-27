(ns omstool.ui
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.panel :as p]
            [om-bootstrap.grid :as g]
            [om-bootstrap.nav :as n]
            [om-bootstrap.button :as b]
            [om-bootstrap.input :as i]
            [om-bootstrap.random :as r]))


;; Utility/helper fxns...

(defn text-input-type [editing?]
  (if editing?
    "text"
    "static"))


(defn select-input-type [editing?]
  (if editing?
    "select"
    "static"))


(defn options-for-select [data selected]
  (map (fn [o] (dom/option {:value (:value o)}
                           (:label o)))
       data))


(defn input-value [owner input]
  (.->value (om/get-node owner input)))



;; Page-level components....
(defn action-links [owner editing? cb]
  (letfn [(click-response [e cb s]
            (cb s)
            (.preventDefault e))]
    (if-not editing?
      (b/button {:href "#"
                 :onClick #(click-response % cb "edit")} "edit")
      (b/toolbar nil
                 (b/button {:href "#"
                            :bs-style "primary"
                            :onClick #(click-response % cb "save")}
                           "save")
                 (b/button {:href "#"
                            :onClick #(click-response % cb "cancel")}
                           "cancel")))))



(defn render-nav-item [nav-item _]
  (reify
    om/IRender
    (render [_]
      (n/nav-item {:href (:href nav-item)} (:label nav-item)))))



(defn render-nav [nav _]
  (reify
    om/IRender
    (render [_]
      (g/row nil
             (g/col {:md 12}
                    (n/navbar {:brand (dom/a {:href "#/"} "Admin")
                               :inverse? true}
                              (n/nav {:collapsible? true}
                                     (om/build-all render-nav-item
                                                   (:links nav)))))))))

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

