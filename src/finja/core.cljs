; Namespace for handling browser URL, History and Address bar.

(ns finja.core
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.history.Html5History))

(def state-atom (atom {:on-path-change-listeners {}}))

(defonce history-instance (let [history-instance (Html5History.)]
                            (goog.events/listen history-instance EventType/NAVIGATE (fn [event]
                                                                                      (doseq [[_ listener] (:on-path-change-listeners (deref state-atom))]
                                                                                        (listener event))))
                            (doto history-instance (.setEnabled true))))

(defn get-current-path
  []
  (.getToken history-instance))

(defn set-current-path!
  [{path :path}]
  (when (not= (get-current-path)
              path)
    (.setToken history-instance path)))

(defn on-path-change
  ([callback]
   (on-path-change nil callback))
  ([options callback]
   (let [{key               :key
          user-changes-only :user-changes-only} (merge {:key               (random-uuid)
                                                        :user-changes-only true}
                                                       options)]
     (swap! state-atom assoc-in [:on-path-change-listeners key] (fn [event]
                                                                  (when (or (not user-changes-only)
                                                                            (.-isNavigation event)) ;; isNavigation = true means that the user made the change.
                                                                    (callback {:path (.-token event)}))))
     key)))

(defn remove-listener
  [key]
  (swap! state-atom update :on-path-change-listeners dissoc key))
