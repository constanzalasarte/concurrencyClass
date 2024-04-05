use std::thread;
use std::thread::spawn;
use crate::merge::merge;

pub fn sort(array: &[i32]) -> Vec<i32> {
    let len = array.len();
    if len <= 1 {
        array.to_vec()
    }
    else {
        let (first, second) = thread::scope(|s|{
            let x  = s.spawn(|| sort(&array[..len/2]));
            let y = s.spawn(|| sort(&array[len/2..]));
            (x.join().unwrap(), y.join().unwrap())
        });
        merge(&first, &second)
    }
}

pub fn sort1thread(array: &[i32]) -> Vec<i32> {
    let len = array.len();
    if len <= 1 {
        array.to_vec()
    }
    else {
        let (first, second) = thread::scope(|s|{
            let x  = s.spawn(|| sort(&array[..len/2]));
            let y = sort(&array[len/2..]);
            (x.join().unwrap(), y)
        });
        merge(&first, &second)
    }
}