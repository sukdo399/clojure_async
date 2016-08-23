(ns async-tea-party.core
  (:gen-class)
  (:require [clojure.core.async
             :refer [>! <! >!! <!! chan close! go go-loop alts!]]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;(let [tea-channel (chan)]
;  (go (>! tea-channel :cup-of-tea1))
;  (go (println "Thanks for the" (<! tea-channel))))
;
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
;(def tea-channel (chan 10))
;
;
;(go-loop []
;  (println "Thanks for the" (<! tea-channel))
;  (recur))
;
;(>!! tea-channel :hot-cup-of-tea)
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
;(def tea-channel (chan 10))
;(def milk-channel (chan 10))
;(def sugar-channel (chan 10))
;
;(go-loop []
;  (let [[v ch] (alts! [tea-channel
;                       milk-channel
;                       sugar-channel])]
;    (println "Got" v "from" ch)
;    (recur)))
;
;(>!! sugar-channel :sugar)
;(>!! milk-channel :milk)
;(>!! tea-channel :tea)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(def google-tea-service-chan (chan 10))
(def yahoo-tea-service-chan (chan 10))

(defn random-add [] (reduce + (repeat (rand-int 1000000) 1)))

(defn request-google-tea-service []
  (go
    (random-add)
    (>! google-tea-service-chan
        "tea compliments of google")))

(defn request-yahoo-tea-service []
  (go
    (random-add)
    (>! yahoo-tea-service-chan
        "tea compliments of yahoo")))

;(defn request-tea []
;  (request-google-tea-service)
;  (request-yahoo-tea-service)
;  (go (let [[v] (alts!
;                  [google-tea-service-chan
;                   yahoo-tea-service-chan])]
;        (println v))))


(def result-chan (chan 10))

(defn request-tea []
  (request-google-tea-service)
  (request-yahoo-tea-service)
  (go (let [[v] (alts!
                  [google-tea-service-chan
                   yahoo-tea-service-chan])]
        (>! result-chan v))))

(defn -main [& args]
  (println "Requesting tea!")
  (request-tea)
  (println (<!! result-chan)))







