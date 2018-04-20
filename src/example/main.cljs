(ns example.main
  (:require [finja.core :as finja]))

(def random-handler-key
  (finja/on-path-change js/console.log))

(finja/set-current-path! {:path "/the-above-only-fires-on-user-action-so-this-will-not-be-logged"})

(def random-handler-key-2
  (finja/on-path-change {:user-changes-only false} js/console.log))

(finja/set-current-path! {:path "/here-we-have-one-handler"})

(finja/on-path-change {:key               :foo
                       :user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/at-this-point-we-have-two-handlers"})

(finja/on-path-change {:key               :bar
                       :user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/then-there-were-three"})

(finja/on-path-change {:key               :bar
                       :user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/same-key-handlers-are-replaced-still-three"})

(finja/remove-listener random-handler-key)
(finja/remove-listener random-handler-key-2)
(finja/remove-listener :foo)
(finja/remove-listener :bar)
