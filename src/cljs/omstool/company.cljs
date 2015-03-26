(ns omstool.company
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-bootstrap.table :as t]
            [om-bootstrap.random :as r]))


(def company-data [ {:sid "Foo"
                     :name "Foo Company"}
                    {:sid "Bar"
                     :name "Bar Company"}])


(defcomponent company-info [company _]
  (init-state [_]
              {:company company})
  (render-state [_ {:keys [company]}]
                (dom/tr
                 (dom/td (:name company))
                 (dom/td (:sid  company))
                 (dom/td (dom/a {:href ""} "edit")))))


(defcomponent companies-table [_ _]
  (render-state [_ {:keys [companies]}]
                (t/table {:striped? true}
                         (dom/thead
                          (dom/tr
                           (dom/th "Company Name")
                           (dom/th "SID")
                           (dom/th "")))
                         (dom/tbody
                          (om/build-all company-info companies)))))


(defcomponent render-companies-index [_ _]
  (render [_]
          (dom/div
           (dom/h3 "Companies")
           (om/build companies-table nil
                     {:init-state {:companies company-data}}))))



