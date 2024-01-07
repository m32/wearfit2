select
    "when", StepCount
from measure
where
    "when" >= '2023-06-14'
    and StepCount is not NULL
;
