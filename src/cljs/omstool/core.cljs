(ns omstool.core
  (:require [om.core :as om :include-macros true]
            [omstool.ui :refer [render-nav render-footer render-homepage]]
            [secretary.core :as secretary :refer-macros [defroute]]))

(defonce app-state (atom {:text "Hello Chestnut!"}))

(defn main []
  (om/root
    render-nav
    nil
    {:target (. js/document (getElementById "header"))})

  (om/root
   render-footer
   nil
   {:target (. js/document (getElementById "footer"))})

  (secretary/dispatch! "/"))
