(ns omstool.company
  (:require [om.core :as om :include-macros true]
            [om-tools.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [omstool.ui :refer [text-input-attribs
                                select-input-attribs
                                options-for-select
                                input-value
                                action-links]]
            [om-bootstrap.table :as t]
            [om-bootstrap.grid :as g]
            [om-bootstrap.input :as i]))


(def company-data [ {:sid "Foo"
                     :name "Foo Company"
                     :industry "retail"}
                    {:sid "Bar"
                     :name "Bar Company"
                     :industry "travel"}
                    {:sid "Baz"
                     :name "Baz Company"
                     :industry "retail"}])

(def industry-data [{:value "travel" :label "Travel"}
                    {:value "retail" :label "Retail"}])




(defn persist-company-state [owner]
  ;; Here would be validation...
  ;; ...and then, remote persistence
  ;; ...and if that worked, update local component state.
  (om/update-state! owner
     (fn [state]
       (let [curr-company (:company state)
             upd-company (assoc curr-company
                            :name (input-value owner "name")
                            :sid (input-value owner "sid")
                            :industry (input-value owner "industry-id"))]
         (assoc state
                :editing? false
                :company upd-company)))))


(defcomponent company-table-line [company owner]
  (init-state [_]
              {:company company})
  (render-state [_ {:keys [company editing?]}]
                (dom/tr
                 (dom/td
                  (i/input (text-input-attribs "name"
                                            (:name company)
                                            editing?)))
                 (dom/td
                  (i/input (text-input-attribs "sid"
                                            (:sid company)
                                            editing?)))
                 (dom/td
                  (i/input (select-input-attribs "industry-id"
                                                 (:industry company)
                                                 editing?)
                           (if editing? (options-for-select
                                         industry-data
                                         (:industry company)))))
                 (dom/td (action-links owner editing?
                            (fn [e]
                              (case e
                                "save"  (persist-company-state owner)
                                "cancel"(om/set-state! owner :editing? false)
                                "edit"  (om/set-state! owner
                                                      :editing? true))))))))

(defcomponent companies-table [_ _]
  (render-state [_ {:keys [companies]}]
                (t/table {:striped? true}
                         (dom/colgroup
                          (dom/col {:className "col-md-4"} nil)
                          (dom/col {:className "col-md-4"} nil)
                          (dom/col {:className "col-md-2"} nil)
                          (dom/col {:className "col-md-2"} nil))
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
          (g/row nil
                 (g/col {:md 12}
                        (dom/h3 "Companies")
                        (om/build companies-table nil
                                  {:init-state {:companies company-data}})))))



