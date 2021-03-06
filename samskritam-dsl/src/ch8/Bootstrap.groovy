package ch8

Object.metaClass.loadConfig = { clazz -> new ConfigSlurper().parse(clazz) }

/**
 * @metamethod directly reference a range of letters in pratyahara list
 * @given string1 varna, string2 varna
 * @result list varnas between the two strings
 * @example range('a','N')) -> [a,e,u,N]
 */
ArrayList.metaClass.range = { first, last ->
  if (first instanceof String && last instanceof String) {
    delegate[delegate.indexOf(first)..delegate.indexOf(last)]
  } else {
    delegate[first..last]
  }
} 

/**
 * @metamethod replace last varna with a given varna
 * @usage used in purva sandhi
 * @given list of varnas
 * @result last varna is replaced with the given parameter
 * @return the modified delegate (list)
 * 
 * @example
 */
ArrayList.metaClass.replaceLast = { x -> delegate[delegate.size()-1] = x; delegate }

ArrayList.metaClass.replaceFirst = { x -> delegate[0] = x; delegate }

/**
 * @metamethod currently used for iko-yan-aci, but use sthAne antaratamaH
 */
//ArrayList.metaClass.substitute = { sub, k -> sub[delegate.indexOf(k)] }
//Given two lists (delegate, otherList), find the index of given in the delegate, but get the corresponding item from the other list
// [i,u,r.,l.]([y,v,r,l], u) -> v .... because indexOf(u) == indexOf(v) 
ArrayList.metaClass.substitute = { otherList, given -> otherList[delegate.indexOf(given)] }

/**
 * @metamethod get a list of prev,current,next set from a list
 * @given integer index
 * @result [prev,current,next], where current = list[index]
 */
ArrayList.metaClass.triplet = { i -> delegate.size() <= 0 ? [null,null,null] : i == 0 ? [null, delegate[i], (delegate.size()>1) ? delegate[i+1] : null] : (i==delegate.size()) ? [(i<1) ? null : delegate[i-1]] : [delegate[i-1], delegate[i], delegate[i+1]] }

