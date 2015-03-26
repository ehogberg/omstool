(ns omstool.company
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.random :as r]))


(defn render-companies-index [app owner]
  (reify
    om/IRender
    (render [_]
      (r/jumbotron nil
                   (dom/p "Companies index")))))



