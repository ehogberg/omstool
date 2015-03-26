(ns omstool.test-runner
  (:require
   [cljs.test :refer-macros [run-tests]]
   [omstool.core-test]))

(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'omstool.core-test))
    0
    1))
