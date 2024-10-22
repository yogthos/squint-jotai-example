(ns App
  (:require
   ["jotai" :as jotai :refer [useAtom]]
   ["jotai-immer" :as jotai-immer :refer [atomWithImmer]]
   ["react" :refer [useState]]))

(def !anime (atomWithImmer
             [{:title "Ghost in the Shell"
               :year 1995
               :watched true}
              {:title "Serial Experiments Lain"
               :year 1998
               :watched false}]))

(defn text-input 
  ([label state setState path]
   (text-input label state setState path identity))
  ([label state setState path format-fn]
   #jsx [:div {:className "field"}
         [:label {:className "label"} label]
         [:div {:className "control"}
          [:input {:className "input"
                   :type "text"
                   :value (get state path "")
                   :onChange #(setState (assoc state path (-> % .-target .-value format-fn)))}]]]))

(defn App []
  (let [[form-state setFormState] (useState {})
        [anime setAnime] (useAtom !anime)]
    #jsx [:div {:className "container box p-6 has-background-light"}
          [:h1 {:className "title has-text-centered has-text-success"}
           "Anime"]
          [:table {:className "table"}
           [:tbody
            (for [{:keys [title year watched]} anime]
              #jsx [:tr {:key title}
                    [:td title]
                    [:td year]
                    [:td
                     {:onClick #(setAnime
                                 (map
                                  (fn [item]
                                    (if (= title (:title item))
                                      (update item :watched not)
                                      item))
                                  anime))}
                     (if watched "✅" "❌")]])]]
          [:div
           (text-input "title" form-state setFormState :title)
           (text-input "year" form-state setFormState :year js/parseInt)
           [:button {:className "button is-success"
                     :disabled (empty? form-state)
                     :onClick (fn []
                                (setAnime (conj anime form-state))
                                (setFormState {}))}
            "Add"]]]))