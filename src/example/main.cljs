(ns example.main
  (:require [finja.core :as finja]))

(finja/on-path-change js/console.log)

(finja/set-current-path! {:path "/the-above-only-fires-on-user-action-so-this-will-not-be-logged"})
(js/console.log "(Note that on figwheel reloads handlers remain so this might still show.)")

(finja/on-path-change {:user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/here-we-have-one-handler"})

(finja/on-path-change {:key               "foo"
                       :user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/at-this-point-we-have-two-handlers"})

(finja/on-path-change {:key               "bar"
                       :user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/then-there-were-three"})

(finja/on-path-change {:key               "bar"
                       :user-changes-only false} js/console.log)

(finja/set-current-path! {:path "/same-key-handlers-are-replaced-still-three"})
