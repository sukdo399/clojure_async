(ns async-tea-party.core
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


