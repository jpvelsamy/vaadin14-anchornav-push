# vaadin14-anchornav-push
This project has anchornav implementation. The setHeight in anchor nav will basically change the scroller behavior and its important to set it. The push notification code is written part of feeder thread and it needs to definitely have some sort of wait time for rest of the threads to execute. So its imperative to make the thread wait before it pushes notifcation
