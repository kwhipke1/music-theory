
(ns
    ^{:doc "Operations on diatonic collections, which are simply collections
of seven pitch classes starting on a given pitch class with this pattern of intervals/steps
from the root note: whole whole half whole whole whole half (where whole and half mean
a major and a minor second interval, respectively). A diatonic collection starting on some
note has the same pitches as a major scale starting on that note.

However, due to the way that Overtone treats scales, we can't just
use the scale function to represent the concept of a diatonic collection.
Given a midi note with two possible pitch classes (like D# and Eb),
overtone always picks the same pitch class when calling note-info on that note.
But, if we are following westergaardian theory, the choice of which tonal-pitch-class a given
pitch should be referred to by is based on the diatonic collection described by the key signature
that the pitch is being played in."
       :author "Kyle Hipke"}

    music-theory.westergaardian-theory.diatonic-collection
  (:use music-theory.westergaardian-theory.tonal-pitch-class)
  (:use music-theory.westergaardian-theory.key-signature))

(defn diatonic-collection
	"Returns a diatonic collection as a vector of the
	pitch classes making up the collection,
	formed by starting on the given pitch class.
	Where two pitch classes are equivalent (like D# and Eb), the
	pitch class chosen will be the one whose accidental appears in the key
	signature that describes that diatonic collection. For the special case of F,
	the flat representation will be used (since we could use sharps or flats
	to get the same diatonic collection, but there are fewer accidentals in the flat version
	which is preferrable). One way to think about it is -
	the key signature for a given starting note is equivalent to the key signature for the
	major scale starting on that note."
	[tonal-pitch-class]
	(vec
    (if (or (tonal-pitch-class-equals tonal-pitch-class :F)
					(> (flats tonal-pitch-class) 0))
		;flats
		(let [flats-map (flats-in-signature tonal-pitch-class)]
		(map #(flattify % (flats-map %))
			(loop [result-vector [(natural tonal-pitch-class)]]
				(if (= (next-natural-tonal-pitch-class (last result-vector)) (natural tonal-pitch-class))
					result-vector
					(recur (conj result-vector (next-natural-tonal-pitch-class (last result-vector))))
					))))
		;sharps
		(let [sharps-map (sharps-in-signature tonal-pitch-class)]
		(map #(sharpen % (sharps-map %))
			(loop [result-vector [(natural tonal-pitch-class)]]
				(if (= (next-natural-tonal-pitch-class (last result-vector)) (natural tonal-pitch-class))
					result-vector
					(recur (conj result-vector (next-natural-tonal-pitch-class (last result-vector))))
					))))
		)))
