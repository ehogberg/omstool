(ns omstool.company
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-bootstrap.table :as t]
            [om-bootstrap.button :as b]
            [om-bootstrap.input :as i]
            [om-bootstrap.random :as r]))


(def company-data [ {:sid "Foo"
                     :name "Foo Company"
                     :industry "Retail"}
                    {:sid "Bar"
                     :name "Bar Company"
                     :industry "Travel"}])

(defn input-value [owner input]
  (-> (om/get-node owner input)
      .->value))

(defn action-links [owner editing?]
  (if-not editing?
    (b/button {:href "#"
               :onClick (fn [e]
                          (om/set-state! owner :editing? true)
                          (.preventDefault e))} "edit")
    (b/toolbar nil
     (b/button {:href "#" :bs-style "primary"} "save")
     (b/button {:href "#"
                :onClick (fn [e]
                           (om/set-state! owner :editing? false)
                           (.preventDefault e))} "cancel"))))


(defcomponent company-table-line [company owner]
  (init-state [_]
              {:company company})
  (render-state [_ {:keys [company editing?]}]
                (dom/tr
                 (dom/td
                  (if editing?
                    (i/input {:type "text"
                              :ref "company-name"
                              :default-value (:name company)})
                    (:name company)))
                 (dom/td
                  (if editing?
                    (i/input {:type "text"
                              :ref "company-id"
                              :default-value (:sid company)})
                    (:sid  company)))
                 (dom/td (:industry company))
                 (dom/td (action-links owner editing?)))))


(defcomponent companies-table [_ _]
  (render-state [_ {:keys [companies]}]
                (t/table {:striped? true}
                         (dom/thead
                          (dom/tr
                           (dom/th "Name")
                           (dom/th "SID")
                           (dom/th "Industry")
                           (dom/th "")))
                         (dom/tbody
                          (om/build-all company-table-line companies
                                        {:init-state {:editing? false}})))))


(defcomponent render-companies-index [_ _]
  (render [_]
          (dom/div
           (dom/h3 "Companies")
           (om/build companies-table nil
                     {:init-state {:companies company-data}}))))



