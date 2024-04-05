use std::time::Instant;
use rand::Rng;

mod merge;
pub mod parallel_sort;
pub mod serial_sort;

fn main() {
    let array3 = random_array(100);

    let t1 = Instant::now();
    let _ = serial_sort::sort(&array3);
    println!("Serial Sort: {:?}", t1.elapsed());

    let t2 = Instant::now();
    let _ = parallel_sort::sort(&array3);
    println!("Parallel Sort: {:?}", t2.elapsed());

    let t3 = Instant::now();
    let _ = parallel_sort::sort1thread(&array3);
    println!("Parallel Sort with 1 thread: {:?}", t3.elapsed());
}

fn random_array(len: usize) -> Vec<i32> {
    let mut rng = rand::thread_rng();
    let mut result = Vec::new();

    for _ in 0..len {
        result.push(rng.gen());
    }
    result
}
