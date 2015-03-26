(ns omstool.routing
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [omstool.ui :refer [render-homepage]]
            [omstool.company :refer [render-companies-index]]
            [om.core :as om :include-macros true])
  (:import goog.History
           goog.history.EventType))

(defroute home-path "/" []
  (om/root render-homepage
           nil
           {:target (. js/document (getElementById "content"))}))

(defroute company-index-path "/companies" []
  (om/root render-companies-index
           nil
           {:target (. js/document (getElementById "content"))}))

(defn start-routing []
  (secretary/set-config! :prefix "#")

  (let [h (goog.History.)]
    (events/listen h EventType.NAVIGATE
                        #(secretary/dispatch! (.-token %)))
    (doto h (.setEnabled true))))

