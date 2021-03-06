(ns music-theory.westergaardian-theory.t-key
  (:use midje.sweet)
  (:use music-theory.westergaardian-theory.key))


(facts "about `key-tonal-pitch-classes`"
       (fact "returns the tonal pitch classes of the key, with the tonic
       first."
             (key-vector :C :major) => [:C :D :E :F :G :A :B]

             (key-vector :A :minor) => [:A :B :C :D :E :F :G]

             (key-vector :C# :major) => [:C# :D# :E# :F# :G# :A# :B#]
             (key-vector :A# :minor) => [:A# :B# :C# :D# :E# :F# :G# ]

             ))

(facts "about `scale`"
       (fact "returns the notes of the scale starting on the given note"
             (ascale (key-vector :C :major) 4) => [:C4 :D4 :E4 :F4 :G4 :A5 :B5]

             (ascale (key-vector :A :minor) 4) => [:A4 :B4 :C4 :D4 :E4 :F4 :G4]

             (ascale (key-vector :C# :major) 4) => [:C#4 :D#4 :E#4 :F#4 :G#4 :A#5 :B#5]
             (ascale (key-vector :A# :minor) 4) => [:A#4 :B#4 :C#4 :D#4 :E#4 :F#4 :G#4 ]

             ))


