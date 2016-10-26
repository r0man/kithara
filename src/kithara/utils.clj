(ns ^:no-doc kithara.utils)

(defn random-string
  []
  (str (java.util.UUID/randomUUID)))

;; ## Stringify

(defn stringify-keys1
  ^java.util.Map
  [m]
  (->> (for [[k v] m]
         [(name k) v])
       (into {})))

;; ## Backoff
;;
;; From ptaoussanis/encore (384f5d3).
;; License: EPL v1.0
;; Copyright © 2014-2016 Peter Taoussanis.

(defn exponential-backoff-ms
  [nattempt {min' :min max' :max}]
  (let [binary-exp (double (Math/pow 2 (dec ^long nattempt)))
        time       (* (+ binary-exp ^double (rand binary-exp)) 0.5 1000)]
    (long (let [time (if min' (max min' time) time)
                time (if max' (min max' time) time)]
            time))))
